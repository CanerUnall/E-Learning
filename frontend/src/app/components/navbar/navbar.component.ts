import { Component, Input, OnInit } from '@angular/core';
import { NavigationEnd, Router, RouterModule } from '@angular/router';
import { JwtService } from '../../services/jwt.service';
import { CommonModule } from '@angular/common';
import { LoginComponent } from '../login/login.component';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterModule, CommonModule, LoginComponent],
  providers: [LoginComponent],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css',
})
export class NavbarComponent implements OnInit {
  @Input() userId: number | null = null;
  @Input() userRole: string | null = '';
  @Input() isLoggedIn: boolean | null = false;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.userRole = this.authService.getRole(); // Kullanıcının rolü
  }

  // Kullanıcı çıkış yapınca
  logout() {
    console.log('logout');

    this.authService.logout();
    this.router.navigate(['']); // Çıkış yaptıktan sonra login sayfasına yönlendirme
  }

  // Profil tıklanınca eğer kullanıcı giriş yapmamışsa login sayfasına yönlendir
  handleProfileClick() {
    if (!this.isLoggedIn) {
      this.router.navigate(['/login']);
    }
  }

  navigateToDashboard() {
    this.router.navigate(['/student'], { state: { userId: this.userId } });
    console.log(this.userId);
  }
}
