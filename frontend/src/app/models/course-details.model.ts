import { User } from './user.model';

export class CourseDetails {
  courseDetailsId!: number;
  startDate!: string;
  endDate!: string;
  startTime!: string;
  endTime!: string;
  teacherName!: string;
  maxStudents!: number;
  completed!: boolean;
  students!: User[]; // İlgili öğrenciler listesi
}
