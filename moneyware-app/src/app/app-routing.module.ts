import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DocumentUploadComponent } from './document-upload/document-upload.component';
import { UserLoginComponent } from './user-login/user-login.component';
const routes: Routes = [
  {path:'documentUpload', component:DocumentUploadComponent},
  {path:'login', component:UserLoginComponent},
  {path:'', redirectTo:'login', pathMatch:'full'} 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
