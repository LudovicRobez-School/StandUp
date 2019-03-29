import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../shared.module';
import { EventCreateComponent } from './event-create/event-create.component';
import { EventDetailComponent } from './event-detail/event-detail.component';
import { EventListComponent } from './event-list/event-list.component';
import { EventService } from '../service/event.service';

const routes: Routes = [
  { path: "create", component: EventCreateComponent, pathMatch: "full" },
  { path: "list", component: EventListComponent, pathMatch: "full" }
];

@NgModule({
  imports: [
    SharedModule,
    RouterModule.forChild(routes),
  ],
  declarations: [EventCreateComponent, EventDetailComponent, EventListComponent],
  providers: [
    EventService
  ]
})
export class EventModule { }
