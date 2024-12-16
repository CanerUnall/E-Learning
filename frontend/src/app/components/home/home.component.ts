import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Course } from '../../models/course.model';
import { CourseService } from '../../services/course.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterModule, CommonModule,FormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  isLoggedIn: boolean = false;  // Kullanıcının giriş durumu
  userRole: string | null = '';  // Kullanıcının rolü

  courses: Course[] = [];  // Tüm kurslar
  searchTerm: string = '';  // Arama terimi

  constructor(private courseService: CourseService) {}

  ngOnInit(): void {
    // Kursları API'den çekiyoruz
    this.courseService.getCourses().subscribe((data: Course[]) => {
      this.courses = data;
    });
  }

  // Filtreleme fonksiyonu
  filteredCourses(): Course[] {
    if (!this.searchTerm) {
      return this.courses;  // Arama terimi boşsa tüm kursları göster
    }

    // Arama terimine göre kursları filtrele
    return this.courses.filter(course =>
      course.courseName.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      course.description.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }
}
