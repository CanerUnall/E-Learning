import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CourseDetails } from '../models/course-details.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CourseDetailsService {
  private baseUrl = 'http://localhost:8080/api/courseDetails';

  constructor(private http: HttpClient) {}

  addCourseDetails(courseId: number, courseDetails: CourseDetails): Observable<CourseDetails> {
    return this.http.post<CourseDetails>(`http://localhost:8080/api/courseDetails/add/${courseId}`, courseDetails);
  }

  // CourseDetails güncelleme
  updateCourseDetails(courseId: number, courseDetails: CourseDetails): Observable<CourseDetails> {
    return this.http.put<CourseDetails>(`${this.baseUrl}/update/${courseId}`, courseDetails);
  }

  // CourseDetails silme
  deleteCourseDetails(courseDetailsId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${courseDetailsId}`);
  }
}
