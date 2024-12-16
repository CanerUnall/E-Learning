import { provideRouter, Routes } from '@angular/router';
import { StudentComponent } from './components/student/student.component';
import { AdminComponent } from './components/admin/admin.component';
import { BackOfficeComponent } from './components/back-office/back-office.component';
import { CourseComponent } from './components/course/course.component';
import { FooterComponent } from './components/footer/footer.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { UserManagementComponent } from './components/user-management/user-management.component';
import { CourseManagementComponent } from './components/course-management/course-management.component';
import { CourseDetailsComponent } from './components/course-details/course-details.component';
import { HomeComponent } from './components/home/home.component';

export const routes: Routes = [

{
    path:"student",
    component:StudentComponent
},
{
    path:"admin",
    component:AdminComponent
},
{
    path:"back-office",
    component:BackOfficeComponent
},
{
    path:"course",
    component:CourseComponent
},
{
    path:"footer",
    component:FooterComponent
},
{
    path:"navbar",
    component:NavbarComponent
},
{
    path:"login",
    component:LoginComponent
},
{
    path:"profile",
    component:ProfileComponent
},
{
    path:"userManagement",
    component:UserManagementComponent
},
{
    path:"courseManagement",
    component:CourseManagementComponent
},
{ 
    path: 'course/:id', 
    component: CourseDetailsComponent 
},
{ 
    path: '', 
    component: HomeComponent 
},
{ 
    path: 'student/:id', 
    component: StudentComponent 
}
];
