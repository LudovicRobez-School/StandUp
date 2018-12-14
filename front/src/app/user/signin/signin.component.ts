import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { debounceTime } from 'rxjs/operators';
import { User } from 'src/app/model/user';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {
  signinForm: FormGroup;
  user: User;
  userNotFound: boolean = false;
  emailInvalidErrorMessage: string = "Veuillez introduire une adresse mail valide";
  passwordInvalidErrorMessage: string = "Le mot de passe doit avoir entre 4 et 40 caractères";
  userNotFoundErrorMessage: string = "Nom d'utilisateur ou mot de passe est invalide";

  constructor(private fb: FormBuilder, private router: Router) {
    this.signinForm = fb.group({
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
      ]
    });

    this.signinForm.valueChanges.pipe(debounceTime(500)).subscribe(data => {
      this.prepareSaveUser()
    });
  }

  ngOnInit() {
  }

  prepareSaveUser(): any {
    const saveUser: any = {
      email: this.signinForm.controls["email"].value as string,
      password: this.signinForm.controls["password"].value as string
    };
    return saveUser;
  }

}
