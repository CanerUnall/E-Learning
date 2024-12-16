import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor() {}

  isLoggedIn(): boolean {
    const token = localStorage.getItem('jwt');
    return !!token; // Eğer token varsa true, yoksa false döner
  }

  logout() {
    localStorage.removeItem('jwt'); // JWT token'ı temizleyip logout yapıyoruz
    localStorage.removeItem('role'); // Role bilgisini de temizliyoruz
  }

  getRole(): string | null {
    return localStorage.getItem('role'); // Kullanıcı rolünü alıyoruz
  }
}
