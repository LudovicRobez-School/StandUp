import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { Router } from '@angular/router';
import { debounceTime } from 'rxjs/operators';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  user: User;
  signupForm: FormGroup;
  emailInvalidErrorMessage: string = "Veuillez introduire une adresse mail valide";
  passwordInvalidErrorMessage: string = "Le mot de passe doit avoir entre 4 et 40 caractères";
  firstNameInvalidErrorMessage: string = "Le prénom doit avoir entre 3 et 30 caractères";
  lastNameInvalidErrorMessage: string = "Le nom doit avoir entre 3 et 30 caractères";

  constructor(private fb: FormBuilder, private authenticationService: AuthenticationService, private router: Router, private userService: UserService) {
    this.signupForm = fb.group({
      email: [
        "marouane.terai@ynov.com",
        Validators.compose([
          Validators.required,
          Validators.email
        ])
      ],
      password: [
        "1234",
        Validators.compose([
          Validators.required,
          Validators.minLength(4),
          Validators.maxLength(20)
        ])
      ],
      firstName: [
        "Marouane",
        Validators.compose([
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(30)
        ])
      ],
      lastName: [
        "Terai",
        Validators.compose([
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(30)
        ])
      ]
    });

    this.signupForm.valueChanges.pipe(debounceTime(500)).subscribe(data => {
      this.prepareSaveUser()
    });
  }

  ngOnInit() {
    // Redirect user to home page if connected
    if (this.authenticationService.isConnected()) {
      this.router.navigate(['/home']);
    }
  }

  signup() {
    this.user = this.prepareSaveUser();
    console.log(this.user);
    if (!this.prepareSaveUser()) {
      return;
    }
    this.userService.addUser(this.user).subscribe(
      data => {
        console.log("success");
        this.router.navigate(["/user/signin"]);
      },
      error => {
        console.error("error");
      }
    )
  }

  prepareSaveUser(): User {
    const saveUser: User = {
      id: this.guid(),
      email: this.signupForm.controls["email"].value as string,
      password: this.signupForm.controls["password"].value as string,
      firstName: this.signupForm.controls["firstName"].value as string,
      lastName: this.signupForm.controls["lastName"].value as string,
      created: new Date(Date.now()),
      modified: new Date(Date.now())
    };
    return saveUser;
  }

  private guid() {
    return Math.floor((1 + Math.random()) * 0x10000)
  }

}
