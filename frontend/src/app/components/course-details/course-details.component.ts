import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { Course } from '../../models/course.model';
import { CourseService } from '../../services/course.service';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../../services/user.service';
import { CourseDetails } from '../../models/course-details.model';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-course-details',
  templateUrl: './course-details.component.html',
  styleUrls: ['./course-details.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule],
})
export class CourseDetailsComponent implements OnInit {
  course: Course = {} as Course;
  teachers: string[] = [];
  @Input() userRole: string | null = '';
  userId: any;
  isStudentRegisteredForSelectedSchedule: boolean = false; // Seçilen schedule için kayıt durumu
  selectedSchedule: CourseDetails | null = null; // Öğrenci veya admin için seçilen schedule
  isCapacityFull: boolean = false; // Seçilen schedule kapasitesi kontrolü

  selectedScheduleStudents: any[] = []; // Seçilen schedule'a ait öğrenciler
  selectedTeacher: string | null = null; // Seçilen schedule'a ait öğretmen

  constructor(
    private userService: UserService,
    private courseService: CourseService,
    private route: ActivatedRoute
  ) {}

  loadCourse(id: number): void {
    this.courseService.getCourse(id).subscribe((course: Course) => {
      if (course) {
        this.course = course;
        // Öğretmenleri CourseDetails'ten al
        this.teachers = course.courseDetailsList
          .map((detail) => detail.teacherName)
          .filter((name): name is string => !!name); // undefined olan değerleri filtreler
      }
    });
  }

  // Dropdown seçiminde yapılacak işlem
  onScheduleChange(): void {
    if (this.userRole === 'Admin' || this.userRole === 'Back Office') {
      // Admin/Back Office ise öğrenci ve öğretmen bilgilerini yükle
      this.loadDetailsForSelectedSchedule();
    } else if (this.userRole === 'Student') {
      // Öğrenci ise kayıt durumu ve kapasiteyi kontrol et
      this.checkSelectedSchedule();
    }
  }

  // Admin veya Back Office kullanıcısı schedule seçince, o schedule'a kayıtlı öğrencileri ve öğretmeni yükle
  loadDetailsForSelectedSchedule(): void {
    if (this.selectedSchedule) {
      // Öğrenci listesini yükle
      this.selectedScheduleStudents = this.selectedSchedule.students || [];

      // Öğretmeni yükle
      this.selectedTeacher = this.selectedSchedule.teacherName || null;
    } else {
      this.selectedScheduleStudents = [];
      this.selectedTeacher = null;
    }
  }

  ngOnInit(): void {
    const courseId = +this.route.snapshot.params['id'];
    this.loadCourse(courseId);

    this.userRole = localStorage.getItem('role');
    this.userId = localStorage.getItem('userId');
  }

  // Seçilen programın kapasitesini ve kayıt durumunu kontrol et
  checkSelectedSchedule(): void {
    if (this.selectedSchedule) {
      this.isCapacityFull =
        !!this.selectedSchedule.maxStudents &&
        !!this.selectedSchedule.students &&
        this.selectedSchedule.students.length >=
          this.selectedSchedule.maxStudents;

      this.isStudentRegisteredForSelectedSchedule =
        !!this.selectedSchedule.students &&
        this.selectedSchedule.students.some(
          (student) => student.userId === +this.userId
        );
    }
  }

  // Öğrenci kaydolma işlemi
  registerToCourse(): void {
    // Seçilen program kontrolü
    if (!this.selectedSchedule) {
      alert('Please select a schedule to register.');
      return;
    }

    // Kapasite kontrolü
    if (this.isCapacityFull) {
      alert('This course schedule is full. You cannot register.');
      return;
    }

    // Öğrenci zaten bu programa kayıtlıysa
    if (this.isStudentRegisteredForSelectedSchedule) {
      alert('You are already registered for this schedule.');
      return;
    }

    // Öğrenci kaydolabiliyorsa
    if (this.userId && this.selectedSchedule.courseDetailsId) {
      this.userService
        .registerToCourse(this.userId, this.selectedSchedule.courseDetailsId)
        .subscribe(
          (response) => {
            alert('Successfully registered to the course schedule!');
            this.selectedSchedule!.students!.push({
              userId: +this.userId,
              userName: '', // Öğrenci adını almak isteyebilirsiniz, backend'den dönen veriyi işleyin
              lastName: '',
              email: '',
              phone: '',
              password: '',
              role: { userRoleId: null, roleName: '' }, // Gerekli role değerini belirleyin
              department: '',
              courseList: [],
            });
            this.isStudentRegisteredForSelectedSchedule = true;
          },
          (error) => {
            console.error('Registration failed', error);
            alert('Failed to register to the course.');
          }
        );
    }
  }
}
