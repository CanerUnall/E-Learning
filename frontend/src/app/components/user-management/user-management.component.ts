import { Component, OnInit, ElementRef, Renderer2 } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import * as bootstrap from 'bootstrap';

@Component({
  standalone: true,
  imports: [FormsModule, CommonModule],
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css'],
})
export class UserManagementComponent implements OnInit {
  users: User[] = [];
  currentUser: User = {
    userId: 0,
    userName: '',
    lastName: '',
    email: '',
    phone: '',
    password: '',
    role: {
      userRoleId: null,
      roleName: '',
    },
    department: ''
  } as User;
  
  editingUser: User | null = null; // Seçilen kullanıcı
  modalTitle: string = '';
  modalActionButton: string = '';

  constructor(
    private userService: UserService,
    private renderer: Renderer2,
    private elRef: ElementRef // ElementRef tablodaki alanı referans almak için kullanılır
  ) {}

  ngOnInit(): void {
    this.loadUsers();
    // Document üzerinde herhangi bir yere tıklamayı dinle
    this.renderer.listen('document', 'click', (event: Event) => {
      this.handleDocumentClick(event);
    });
  }

  loadUsers() {
    this.userService.getUsers().subscribe((data: User[]) => {
      this.users = data;
    });
  }

  selectUser(user: User) {
    this.editingUser = { ...user }; // Seçilen kullanıcıyı kopyala
  }

  openModal(action: 'add' | 'edit') {
    if (action === 'add') {
      this.modalTitle = 'Add New User';
      this.modalActionButton = 'Add User';
      this.currentUser = {
        userId: 0,
        userName: '',
        lastName: '',
        email: '',
        phone: '',
        password: '',
        role: {
          userRoleId: null,
          roleName: '',
        },
        department: '',
        courseList: []
      } as User;
    } else if (action === 'edit' && this.editingUser) {
      this.modalTitle = 'Edit User';
      this.modalActionButton = 'Save Changes';
      this.currentUser = { ...this.editingUser };
    }
    const modal = new bootstrap.Modal(document.getElementById('userModal')!);
    modal.show();
  }

  saveUser() {
    if (this.currentUser.userId === 0) {
      // Yeni kullanıcı ekle
      this.userService.createUser(this.currentUser).subscribe(() => {
        this.loadUsers();
        this.resetModal();
      });
    } else {
      // Mevcut kullanıcıyı düzenle
      this.userService.updateUser(this.currentUser.userId, this.currentUser).subscribe(() => {
        this.loadUsers();
        this.resetModal();
      });
    }
  }

  deleteUser(userId: number) {
    this.userService.deleteUser(userId).subscribe(() => {
      this.loadUsers();
    });
  }

  resetModal() {
    this.currentUser = {
      userId: 0,
      userName: '',
      lastName: '',
      email: '',
      phone: '',
      password: '',
      role: {
        userRoleId: null,
        roleName: '',
      },
      department: '',
      courseList: []
    } as User;
    const modalElement = document.getElementById('userModal');
    const modal = bootstrap.Modal.getInstance(modalElement!);
    modal?.hide();
  }

  // Tıklanan yer tablonun dışı mı kontrol et
  handleDocumentClick(event: Event) {
    const clickedInsideTable = this.elRef.nativeElement.contains(event.target);
    if (!clickedInsideTable) {
      this.editingUser = null; // Tıklanan yer tablonun dışındaysa editingUser null yapılır
    }
  }
}
