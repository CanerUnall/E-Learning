import { CourseDetails } from './course-details.model';
import { UserRole } from './user-role.model';

export interface User {
  userId: number;
  userName: string;
  lastName: string;
  email: string;
  phone: string;
  password: string;
  role: UserRole;
  department: string;
  courseList: CourseDetails[]; // Kullanıcının kayıtlı olduğu kurs detayları
}
