import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { Router } from '@angular/router';
import { CommunicationService } from '../communication.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

  constructor(private communicationService:CommunicationService,private route: Router) { }
  loggedUser: User = new User();
  
  ngOnInit(): void {
    
  }

  loginUser(){
    console.log(this.loggedUser);
    console.log("Routing to doc upload");
    this.route.navigate(["/documentUpload"]);
    this.setUserName();
  }

  setUserName(){
    console.log("SettingUsername");
    this.communicationService.displayName=this.loggedUser.userName;
  }
}
