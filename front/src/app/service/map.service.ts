import { Injectable } from '@angular/core';
import { catchError, map, tap } from 'rxjs/operators';
import { of, Observable } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import * as mapboxgl from 'mapbox-gl';
import { environment } from 'src/environments/environment';
import { GeoJson } from '../model/map';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class MapService {

  private mapsUrl: string = "api/markers";

  constructor(private http: HttpClient) {
    mapboxgl.accessToken = environment.mapbox.accessToken;
  }

  getMaps(): Observable<GeoJson[]> {
    return this.http.get<GeoJson[]>(this.mapsUrl)
      .pipe(
        tap(_ => this.log('fetched maps')),
        catchError(this.handleError('getMaps', []))
      );
  }

  getMap(id: number): Observable<GeoJson> {
    const url = `${this.mapsUrl}/${id}`;
    return this.http.get<GeoJson>(url).pipe(
      tap(_ => this.log(`fetched map id=${id}`)),
      catchError(this.handleError<GeoJson>(`getMap id=${id}`))
    );
  }

  updateMap(map: GeoJson): Observable<any> {
    return this.http.put(this.mapsUrl, map, httpOptions).pipe(
      tap(_ => this.log(`updated map id=${map.id}`)),
      catchError(this.handleError<any>('updateMap'))
    );
  }

  addMap(map: GeoJson): Observable<GeoJson> {
    return this.http.post<GeoJson>(this.mapsUrl, map, httpOptions).pipe(
      tap((map: GeoJson) => this.log(`added map w/ id=${map.id}`)),
      catchError(this.handleError<GeoJson>('addMap'))
    );
  }

  deleteMap(map: GeoJson | number): Observable<GeoJson> {
    const id = typeof map === 'number' ? map : map.id;
    const url = `${this.mapsUrl}/${id}`;

    return this.http.delete<GeoJson>(url, httpOptions).pipe(
      tap(_ => this.log(`deleted map id=${id}`)),
      catchError(this.handleError<GeoJson>('deleteMap'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  private log(message: string) {
    console.log(`MapService: ${message}`);
  }
}
