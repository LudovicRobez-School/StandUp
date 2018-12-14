import { NgModule } from '@angular/core';
import { SigninComponent } from './signin/signin.component';
import { Routes, RouterModule } from '@angular/router';
import { SharedModule } from '../shared.module';
import { SignupComponent } from './signup/signup.component';

const routes: Routes = [
  { path: "signin", component: SigninComponent, pathMatch: "full" },
  { path: "signup", component: SignupComponent, pathMatch: "full" }
];

@NgModule({
  imports: [
    SharedModule,
    RouterModule.forChild(routes)
  ],
  declarations: [SigninComponent, SignupComponent]
})
export class UserModule { }
