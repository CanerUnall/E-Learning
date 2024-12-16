import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { CourseDetails } from '../models/course-details.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  registerToCourseForDate(userId: any, courseId: number, selectedDateTimeId: number) {
    throw new Error('Method not implemented.');
  }

  private apiUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) { }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl);
  }

  getUser(id: number): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/${id}`);
  }

  createUser(user: User): Observable<User> {
    return this.http.post<User>(this.apiUrl, user);
  }

  updateUser(id: number, user: User): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}/${id}`, user);
  }

  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  registerToCourse(studentId: number, courseDetailsId: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/${studentId}/courseDetails/${courseDetailsId}`, null, { responseType: 'text' });
  }

  getRegisteredCourseDetails(studentId: number, courseId: number): Observable<CourseDetails[]> {
    return this.http.get<CourseDetails[]>(`${this.apiUrl}/${studentId}/registered-courseDetails/${courseId}`);
  }
}
