import { Component, Input, OnInit } from '@angular/core';
import { DocumentUploadService } from '../document-upload.service';
import {UserDocument} from '../user-document';
import { CommunicationService } from '../communication.service';

@Component({
  selector: 'app-document-upload',
  templateUrl: './document-upload.component.html',
  styleUrls: ['./document-upload.component.css']
})
export class DocumentUploadComponent implements OnInit {

  constructor(private documentUploadService: DocumentUploadService, public communicationService:CommunicationService) { }
  dispName:String = this.communicationService.displayName;
  document : UserDocument = new UserDocument();
  selectedFile : Blob | undefined;
  formData = new FormData();
  ngOnInit(): void {
  }
  
  onSubmit(){
    this.document.userName=this.dispName;
    console.log(this.document);
    this.uploadDocument();
  }

  uploadDocument(){
    this.formData.append('userDocument', JSON.stringify(this.document));
    this.documentUploadService.uploadDocument(this.formData).subscribe();
  }

  public upload(event : Event) {
    const target = event.target as HTMLInputElement;
    const files = target.files as FileList;
    this.formData =new FormData();
    this.formData.append('uploadedFile',files[0]);
  }
}
