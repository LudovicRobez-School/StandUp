import { NgModule } from "@angular/core";
import { Routes, RouterModule, PreloadAllModules } from "@angular/router";
import { HomeComponent } from "./home/home.component";
import { AboutComponent } from "./about/about.component";
import { UserModule } from "./user/user.module";

const routes: Routes = [
    { path: "home", component: HomeComponent, pathMatch: "full" },
    { path: "about", component: AboutComponent, pathMatch: "full" },
    { path: 'user', loadChildren: () => UserModule, },
    { path: "", redirectTo: "/home", pathMatch: "full" },
    { path: "**", redirectTo: "/home", pathMatch: "full" }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    declarations: [],
    exports: [RouterModule]
})
export class AppRoutingModule { }