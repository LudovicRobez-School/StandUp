import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { debounceTime } from 'rxjs/operators';
import { Event } from 'src/app/model/event';
import { EventService } from 'src/app/service/event.service';

@Component({
  selector: 'app-event-create',
  templateUrl: './event-create.component.html',
  styleUrls: ['./event-create.component.css']
})
export class EventCreateComponent implements OnInit {
  event: Event;
  eventCreateForm: FormGroup;
  titleInvalidErrorMessage: string = "Le titre d'événement doit avoir entre 3 et 120 caractères";
  addressInvalidErrorMessage: string = "L'adresse' doit avoir entre 10 et 120 caractères";
  eventDateInvalidErrorMessage: string = "La date de l'événement est obligatoire";

  constructor(private fb: FormBuilder, private eventService: EventService, private router: Router) {
    this.eventCreateForm = fb.group({
      title: [
        "",
        Validators.compose([
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(120)
        ])
      ],
      address: [
        "",
        Validators.compose([
          Validators.required,
          Validators.minLength(10),
          Validators.maxLength(120)
        ])
      ],
      eventDate: [
        Date.now(),
        Validators.compose([
          Validators.required
        ])
      ]
    });

    this.eventCreateForm.valueChanges.pipe(debounceTime(500)).subscribe(data => {
      this.prepareSaveEvent()
    });
  }

  ngOnInit() {
  }

  createEvent() {
    this.event = this.prepareSaveEvent();
    this.eventService.addEvent(this.event).subscribe(
      data => {
        this.router.navigate(["/event/list"]);
        console.log("success");
      },
      error => {
        console.error("error");
      }
    )
  }

  prepareSaveEvent(): Event {
    const saveUser: Event = {
      id: this.guid(),
      userId: this.guid(),
      title: this.eventCreateForm.controls["title"].value as string,
      address: this.eventCreateForm.controls["address"].value as string,
      eventDate: this.eventCreateForm.controls["eventDate"].value as Date,
      created: new Date(Date.now()),
      modified: new Date(Date.now())
    };
    return saveUser;
  }

  private guid() {
    return Math.floor((1 + Math.random()) * 0x10000)
  }

}
