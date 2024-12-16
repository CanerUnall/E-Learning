import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASE_URL = ["http://localhost:8080/api/"];  // BASE_URL'yi dizi yerine string yapın

@Injectable({
  providedIn: 'root'
})
export class JwtService {

  constructor(private http: HttpClient) { }

  login(loginRequest: any): Observable<any> {
    return this.http.post(BASE_URL + 'login', loginRequest);
  }

  private createAuthorizationHeader(): HttpHeaders | undefined {  // Türü güncelleyin
    const jwtToken = localStorage.getItem('jwt');
    if (jwtToken) {
      console.log("JWT token found in local storage", jwtToken);
      return new HttpHeaders().set(
        "Authorization", "Bearer " + jwtToken
      );
    } else {
      console.log("JWT token not found in local storage");
    }
    return undefined;  // null yerine undefined döndürün
  }

  

}
