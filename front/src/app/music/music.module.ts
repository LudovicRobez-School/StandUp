import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SharedModule } from '../shared.module';
import { ListComponent } from './list/list.component';

const routes: Routes = [
  { path: "list", component: ListComponent, pathMatch: "full" }
];

@NgModule({
  declarations: [ListComponent],
  imports: [
    SharedModule,
    RouterModule.forChild(routes)
  ],
})
export class MusicModule { }
