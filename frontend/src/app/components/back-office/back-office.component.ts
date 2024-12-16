import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Course } from '../../models/course.model';
import { CourseService } from '../../services/course.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-back-office',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './back-office.component.html',
  styleUrl: './back-office.component.css'
})
export class BackOfficeComponent implements OnInit {
  courses: Course[] = [];

  constructor(private router: Router, private courseService: CourseService) { }

  ngOnInit(): void {
    this.courseService.getCourses().subscribe((data: Course[]) => {
      this.courses = data;
    });
  }

  viewCourseDetails(courseId: number) {
    this.router.navigate(['/course', courseId]);
  }
}
