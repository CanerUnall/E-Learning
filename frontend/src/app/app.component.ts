import { Component, OnInit } from '@angular/core';
import { RouterModule, RouterOutlet, NavigationEnd, Router } from '@angular/router';
import { StudentComponent } from './components/student/student.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { AuthService } from './services/auth.service';


@Component({
  
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, StudentComponent,NavbarComponent,FooterComponent,
    FormsModule, RouterModule,CommonModule,
    HttpClientModule,
    ReactiveFormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  
})
export class AppComponent implements OnInit{
  title = 'frontend';
  userRole:string | null = '';
  userId:any;
  isLoggedIn: boolean = false;   // Kullanıcı giriş yapmış mı?
  constructor(private router: Router,private authService: AuthService) {}

  ngOnInit(): void {
   
    this.userRole = localStorage.getItem("role");
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.isLoggedIn = this.authService.isLoggedIn();  // Giriş durumu
        this.userRole = localStorage.getItem("role");
        this.userId = localStorage.getItem('userId'); // Kullanıcı ID'sini çekiyoruz
      }
    })
  }
}
