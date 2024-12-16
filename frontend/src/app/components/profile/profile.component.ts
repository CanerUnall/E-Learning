import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';



@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  profile ={
    firstName: 'John',
    lastName: 'Doe',
    email: 'john.doe@example.com',
    phone: '+1 234 567 890',
    department: 'Engineering',
    imageUrl: 'https://randomuser.me/api/portraits/men/1.jpg'
  };

  constructor() { }

  ngOnInit(): void {
    // Initialization logic here if needed

  }

  
}

