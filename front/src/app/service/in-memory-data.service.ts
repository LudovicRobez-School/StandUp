import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class InMemoryDataService {

  createDb() {
    const users = [
      {
        "created": "2019-06-01T16:26:13.139Z",
        "email": "marouane.terai@ynov.com",
        "firstName": "Marouane",
        "id": 124605,
        "lastName": "Terai",
        "modified": "2019-06-01T16:26:13.139Z",
        "password": "1234",
        "userType": "Artiste"
      }
    ];
    const markers = [
      {
        "id": 0,
        "type": "Feature",
        "geometry": {
          "type": "Point",
          "coordinates": [5.445221897487755, 43.526632425505056]
        },
        "properties": {
          "message": "Spectacle Gad Elmaleh"
        }
      },
      {
        "id": 1,
        "type": "Feature",
        "geometry": {
          "type": "Point",
          "coordinates": [5.4358845065900425, 43.52326592067149]
        },
        "properties": {
          "message": "Concert Ed Sheeran"
        }
      },
      {
        "id": 2,
        "type": "Feature",
        "geometry": {
          "type": "Point",
          "coordinates": [5.428974538204244, 43.526701361794494]
        },
        "properties": {
          "message": "Exposition Léonard de Vinci"
        }
      }
    ];
    return { users, markers };
  }
}
