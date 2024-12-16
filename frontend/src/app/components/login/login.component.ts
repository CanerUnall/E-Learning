import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { JwtService } from '../../services/jwt.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule,CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;

  constructor(
    private service: JwtService,
    private fb: FormBuilder,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]], // Senkron doğrulayıcılar array içinde
      password: ['', Validators.required],
    });
  }

  private userRole: string = '';

  setRole(role: string) {
    this.userRole = role;
    localStorage.setItem('role', role);
  }

  getRole(): string {
    return this.userRole;
  }
  

  submitForm() {
    this.service.login(this.loginForm.value).subscribe(
      (response) => {
        console.log(response);
        
        if (response.jwt != null) {
          const jwtToken = response.jwt;
          localStorage.setItem('jwt', jwtToken);

          // JWT'yi decode ederek rol bilgisine ulaşma
          const decodedToken = this.decodeToken(jwtToken);
          const role = decodedToken.role;  // JWT'den rol bilgisini alıyoruz
          const userId = decodedToken.userId;  // Kullanıcı ID'sini JWT'den alıyoruz
          localStorage.setItem('userId', userId);
          this.setRole(role);
          if (role === 'Admin') {
            console.log(role);
            this.router.navigateByUrl('');
          } else if (role === 'Student') {
            console.log(role);
            this.router.navigateByUrl('/student');
          } else if (role === 'Back Office') {
            console.log(role);
            this.router.navigateByUrl('/back-office');
          } else {
            
          }
        }
      }
    );
}

decodeToken(token: string): any {
    const payload = token.split('.')[1];  // JWT'nin ikinci kısmı payload'dır
    return JSON.parse(atob(payload));  // Base64'ten decode ederek JSON objesine çeviriyoruz
}


}

// <script>
// document.getElementById('submit').addEventListener('click', function(event) {
//     event.preventDefault(); // Prevent the form from refreshing the page
    
//     // Get form values
//     const email = document.getElementById('email').value;
//     const password = document.getElementById('password').value;
    
//     // Check if fields are not empty
//     if (email && password) {
//         // Prepare the request
//         const loginData = {
//             email: email,
//             password: password
//         };

//         // Send a POST request to the authentication endpoint
//         fetch('https://your-backend-url.com/api/login', { // Replace with your backend URL
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json',
//             },
//             body: JSON.stringify(loginData),
//         })
//         .then(response => response.json())
//         .then(data => {
//             if (data.token) {
//                 // Save the JWT token in localStorage or sessionStorage
//                 localStorage.setItem('jwtToken', data.token);

//                 // Redirect to another page or show a success message
//                 alert('Login successful!');
//                 window.location.href = 'your-redirect-url.html'; // Redirect after successful login
//             } else {
//                 // Handle errors (e.g., incorrect password, user not found)
//                 alert('Login failed: ' + data.message);
//             }
//         })
//         .catch(error => {
//             console.error('Error:', error);
//             alert('An error occurred during login.');
//         });
//     } else {
//         alert('Please fill out all fields.');
//     }
// });
// </script>
