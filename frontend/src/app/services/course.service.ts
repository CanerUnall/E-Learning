import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { firstValueFrom, Observable } from 'rxjs';
import { Course } from '../models/course.model';
import { CourseDetails } from '../models/course-details.model';


export interface CourseCluster {
  allCourses: Course[];
  followedCourses: Course[];
  unfollowedCourses: Course[];
}

@Injectable({
  providedIn: 'root',
})
export class CourseService {
  addCourseDetails(courseId: number, newCourseDateTime: CourseDetails) {
    throw new Error('Method not implemented.');
  }
  private apiUrl = 'http://localhost:8080/api/course';

  constructor(private http: HttpClient) {}

  getCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(this.apiUrl);
  }

  getCourse(id: number): Observable<Course> {
    return this.http.get<Course>(`${this.apiUrl}/${id}`);
  } //use this to get a single course by id

  createCourse(course: Course): Observable<Course> {
    return this.http.post<Course>(`${this.apiUrl}/add`, course);
  }

  updateCourse(id: number, course: Course): Observable<Course> {
    return this.http.put<Course>(`${this.apiUrl}/${id}`, course);
  }

  deleteCourse(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getCourseById(courseId: number): Observable<Course> {
    return this.http.get<Course>(`${this.apiUrl}/${courseId}`);
  }

  registerToCourseForDate(userId: number, courseId: number, dateTimeId: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/courses/${courseId}/register/${userId}/date/${dateTimeId}`, {});
  }
  
  getStudentsForDate(dateTimeId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/courses/dates/${dateTimeId}/students`);
  } 

  

  // Belirli bir öğrenci için kursları getirir
  getCoursesByStudent(studentId: number): Observable<Course[]> {
    return this.http.get<Course[]>(
      `${this.apiUrl}/student/${studentId}/courses`
    );
  }

  // Öğrencinin takip etmediği kursları getirir
  getUnfollowedCoursesByStudent(studentId: number): Observable<Course[]> {
    return this.http.get<Course[]>(
      `${this.apiUrl}/student/${studentId}/unfollowed-courses`
    );
  }
  private apiUrl2 = 'http://localhost:8080/api/course-datetime';
  deleteCourseDateTime(dateTimeId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl2}/${dateTimeId}`);
  }

  async loadCourses(studentId: number): Promise<CourseCluster> {
    const asdf = {
      allCourses: await firstValueFrom(this.getCourses()),
      followedCourses: await firstValueFrom(
        this.getCoursesByStudent(studentId)
      ),
      unfollowedCourses: await firstValueFrom(
        this.getUnfollowedCoursesByStudent(studentId)
      ),
    };
    console.log(asdf);

    return asdf;
  }
}
