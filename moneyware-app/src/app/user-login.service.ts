import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserLoginService {



  constructor(private http:HttpClient) { 

  }

  authenticateUser(user:User){

  }

}
