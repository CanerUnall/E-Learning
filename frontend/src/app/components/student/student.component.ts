import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CourseCluster, CourseService } from '../../services/course.service';
import { Course } from '../../models/course.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-student',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css'],
})
export class StudentComponent implements OnInit {
  // allCourses: Course[] = [];
  // followedCourses: Course[] = [];
  // unfollowedCourses: Course[] = [];
  courseCluster!: CourseCluster;
  studentId: any = localStorage.getItem('userId'); // Burada kullanıcı ID'si localStorage veya AuthService'den alınabilir

  @Input() triggerReload: boolean = false; // CourseDetailsComponent'ten tetiklenecek olay

  constructor(private courseService: CourseService, private router: Router) {}

  ngOnInit(): void {
    this.loadCourses().then((cc) => (this.courseCluster = cc));
  }

  ngOnChanges(): void {
    // Eğer kayıt olayı tetiklenirse kursları yeniden yükle
    if (this.triggerReload) {
      this.loadCourses().then((cc) => (this.courseCluster = cc));
    }
  }

  goToCourseDetails(courseId: number): void {
    this.router.navigate(['/course', courseId]);
  }

  async loadCourses(): Promise<CourseCluster> {
    return this.courseService.loadCourses(this.studentId);
  }
}
