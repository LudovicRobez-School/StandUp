import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { AlertsService } from 'angular-alert-module';
import { debounceTime } from 'rxjs/operators';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  private userId: number;
  private user: User;
  private profileForm: FormGroup;

  constructor(private fb: FormBuilder, private router: Router, private userService: UserService, private alertsService: AlertsService, private route: ActivatedRoute) {
    this.profileForm = fb.group({
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
      ],
      userType: [
        "Fan",
        Validators.compose([
          Validators.required,
        ])
      ]
    });

    this.profileForm.valueChanges.pipe(debounceTime(500)).subscribe(data => {
      this.prepareSaveUser()
    });
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.userId = Number(params.get("id"));
    })

    this.userService.getUser(this.userId).subscribe(
      data => {
        this.user = data;
        console.log(this.user);
      },
      error => console.error(error)
    );
  }

  prepareSaveUser(): User {
    const saveUser: User = {
      email: this.profileForm.controls["email"].value as string,
      password: this.profileForm.controls["password"].value as string,
      firstName: this.profileForm.controls["firstName"].value as string,
      lastName: this.profileForm.controls["lastName"].value as string,
      userType: this.profileForm.controls["userType"].value as string,
      modified: new Date(Date.now())
    };
    return saveUser;
  }

}
