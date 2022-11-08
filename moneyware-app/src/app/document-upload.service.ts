import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DocumentUploadService {
  private baseUrl='http://localhost:8080/api/v1.0/';
  constructor(private httpClient: HttpClient) { }

    uploadDocument(formData:FormData):Observable<Object>{
    return this.httpClient.post(this.baseUrl+"uploadDocument",formData);

  }
}
