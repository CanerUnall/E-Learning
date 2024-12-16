
import { CourseDetails } from './course-details.model';

export interface Course {
  courseId: number;
  courseName: string;
  description: string;
  dayPartsOfCourse: string;
  online: boolean;
  materialLanguage: string;
  spokenLanguage: string;
  location: string;
  image: string;
  courseDetailsList: CourseDetails[];  // Course ile ilişkilendirilmiş detaylar
  }

