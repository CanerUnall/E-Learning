import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Course } from '../../models/course.model';
import { CourseService } from '../../services/course.service';
import { CourseDetails } from '../../models/course-details.model';
import * as bootstrap from 'bootstrap';
import { CourseDetailsService } from '../../services/course-details.service';

@Component({
  selector: 'app-course-management',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './course-management.component.html',
  styleUrls: ['./course-management.component.css'],
})
export class CourseManagementComponent {
  courses: Course[] = [];
  successMessage: string = ''; // Başarı mesajı için
  errorMessage: string = ''; // Hata mesajı için
  isFormSubmitted = false; // Formun doğrulama durumunu kontrol etmek için

  newCourseDateTime: CourseDetails = {
    courseDetailsId: 0,
    startDate: '',
    endDate: '',
    startTime: '',
    endTime: '',
    teacherName: '',
    maxStudents: 0,
    completed: false,
    students: [],
  };

  currentCourse: Course = {
    courseId: 0,
    courseName: '',
    description: '',
    dayPartsOfCourse: '',
    online: false,
    materialLanguage: '',
    spokenLanguage: '',
    location: '',
    image: '', // Image URL alanı
    courseDetailsList: [],
  };

  editingCourse: Course | null = null;
  modalTitle: string = '';
  modalActionButton: string = '';
  modalType: string = '';

  constructor(
    private courseService: CourseService,
    private courseDetailsService: CourseDetailsService
  ) {}

  ngOnInit(): void {
    this.loadCourses();
  }

  loadCourses() {
    this.courseService.getCourses().subscribe((data: Course[]) => {
      console.log(data);
      this.courses = data;
    });
  }

  selectCourse(course: Course) {
    console.log('Selected course:', course); // Tıklanan kursu konsola yazdır
    this.editingCourse = { ...course }; // Tıklanan kursu seçili hale getiriyoruz
  }

  openModal(action: 'add' | 'edit') {
    if (action === 'add') {
      this.modalTitle = 'Add New Course';
      this.modalActionButton = 'Add Course';
      this.currentCourse = {
        courseId: 0,
        courseName: '',
        description: '',
        dayPartsOfCourse: '',
        online: false,
        materialLanguage: '',
        spokenLanguage: '',
        location: '',
        image: '', // Image URL alanı
        courseDetailsList: [],
      };
    } else if (action === 'edit' && this.editingCourse) {
      this.modalTitle = 'Edit Course';
      this.modalActionButton = 'Save Changes';
      this.currentCourse = { ...this.editingCourse };
    }
    const modal = new bootstrap.Modal(document.getElementById('courseModal')!);
    modal.show();
  }

  saveCourse() {
    if (this.currentCourse.courseId === 0) {
      this.courseService.createCourse(this.currentCourse).subscribe({
        next: () => {
          this.showSuccessMessage('Course successfully added!');
          this.resetModal();
        },
        error: () => {
          this.showErrorMessage('Error adding course.');
        },
      });
    } else {
      this.courseService
        .updateCourse(this.currentCourse.courseId, this.currentCourse)
        .subscribe({
          next: () => {
            this.showSuccessMessage('Course successfully updated!');
            this.resetModal();
          },
          error: () => {
            this.showErrorMessage('Error updating course.');
          },
        });
    }
  }

  deleteCourse(courseId: number) {
    this.courseService.deleteCourse(courseId).subscribe(() => {
      this.loadCourses();
    });
  }

  resetModal() {
    this.currentCourse = {
      courseId: 0,
      courseName: '',
      description: '',
      dayPartsOfCourse: '',
      online: false,
      materialLanguage: '',
      spokenLanguage: '',
      location: '',
      image: '',
      courseDetailsList: [],
    };
    const modalElement = document.getElementById('courseModal');
    const modal = bootstrap.Modal.getInstance(modalElement!);
    modal?.hide();
  }

  openModal2(type: string, course: Course): void {
    this.modalType = type;
    this.editingCourse = course;

    if (type === 'studentList') {
      this.modalTitle = 'Student List';
      this.modalActionButton = 'Add'; // Add butonu için metni güncelleyin
    } else if (type === 'courseDates') {
      this.modalTitle = 'Course Details';
      this.modalActionButton = 'Add Details'; // Course Details için Add butonu metni
    }

    const modalElement = document.getElementById('infoModal');
    if (modalElement) {
      const modal = new bootstrap.Modal(modalElement);
      modal.show();
    }
  }

  // Detayları ekleme
  addCourseDetails(): void {
    console.log(this.newCourseDateTime); // Gönderilen veriyi kontrol edin
    if (this.editingCourse?.courseId) {
      this.courseDetailsService
        .addCourseDetails(this.editingCourse.courseId, this.newCourseDateTime)
        .subscribe({
          next: (updatedCourseDetails) => {
            this.editingCourse?.courseDetailsList.push(updatedCourseDetails);
            this.newCourseDateTime = this.resetCourseDetails();
            this.closeModal('infoModal');
            this.showSuccessMessage('Course details successfully added!');
          },
          error: (err) => {
            console.error('Error adding course date time:', err);
            this.showErrorMessage('Error adding course details.');
          },
        });
    }
  }

  isFormValid(): boolean {
    return (
      !!this.newCourseDateTime.startDate &&
      !!this.newCourseDateTime.endDate &&
      !!this.newCourseDateTime.startTime &&
      !!this.newCourseDateTime.endTime &&
      !!this.newCourseDateTime.teacherName &&
      !!this.newCourseDateTime.maxStudents
    );
  }

  editCourseDate(index: number): void {
    const selectedDate = this.editingCourse?.courseDetailsList[index];
    if (selectedDate) {
      // CourseDetails bilgilerini forma yükle
      this.newCourseDateTime = { ...selectedDate };

      // Modal başlığı ve buton metinlerini güncelleyin
      this.modalTitle = 'Course Details';
      this.modalActionButton = 'Edit Details';

      //Modal'ı aç
      const modalElement = document.getElementById('infoModal');
      if (modalElement) {
        const modal = new bootstrap.Modal(modalElement);
        modal.show();
      }
    }
  }

  // Detayları silme
  removeCourseDate(index: number): void {
    const selectedDateId =
      this.editingCourse?.courseDetailsList[index]?.courseDetailsId;
    if (selectedDateId) {
      this.courseDetailsService.deleteCourseDetails(selectedDateId).subscribe({
        next: () => {
          this.editingCourse?.courseDetailsList.splice(index, 1);
          this.showSuccessMessage('Course details successfully deleted!');
        },
        error: (err) => {
          console.error('Error deleting course date time:', err);
          this.showErrorMessage('Error deleting course details.');
        },
      });
    }
  }

  saveCourseDetails(): void {
    this.isFormSubmitted = true;
    if (this.editingCourse?.courseId) {
      if (this.newCourseDateTime.courseDetailsId === 0) {
        // Yeni CourseDetails ekleniyor
        this.addCourseDetails();
      } else {
        // Var olan CourseDetails güncelleniyor
        this.courseDetailsService
          .updateCourseDetails(
            this.newCourseDateTime.courseDetailsId,
            this.newCourseDateTime
          )
          .subscribe({
            next: (updatedCourseDetails) => {
              const index = this.editingCourse!.courseDetailsList.findIndex(
                (detail) =>
                  detail.courseDetailsId ===
                  updatedCourseDetails.courseDetailsId
              );
              if (index !== -1) {
                this.editingCourse!.courseDetailsList[index] =
                  updatedCourseDetails;
              }
              this.newCourseDateTime = this.resetCourseDetails();
              this.closeModal('infoModal'); // Modal'ı kapat
              this.showSuccessMessage('Course details successfully updated!');
            },
            error: (err) => {
              console.error('Error updating course date time:', err);
              this.showErrorMessage('Error updating course details.');
            },
          });
      }
    }
  }

  closeModal(modalId: string): void {
    const modalElement = document.getElementById(modalId);
    const modal = bootstrap.Modal.getInstance(modalElement!);
    modal?.hide();
  }

  // Helper function: CourseDetails nesnesini sıfırlama
  private resetCourseDetails(): CourseDetails {
    return {
      courseDetailsId: 0,
      startDate: '',
      endDate: '',
      startTime: '',
      endTime: '',
      teacherName: '',
      maxStudents: 0,
      completed: false,
      students: [],
    };
  }

  // Başarı mesajı göstermek için
  showSuccessMessage(message: string): void {
    this.successMessage = message;
    setTimeout(() => {
      this.successMessage = ''; // Mesajı birkaç saniye sonra temizle
      this.loadCourses(); // Sayfayı yenile
    }, 2000); // 2 saniye sonra sayfayı yenile
  }

  // Hata mesajı göstermek için
  showErrorMessage(message: string): void {
    this.errorMessage = message;
    setTimeout(() => {
      this.errorMessage = ''; // Hata mesajını birkaç saniye sonra temizle
    }, 3000); // 3 saniye hata mesajını göster
  }
}
