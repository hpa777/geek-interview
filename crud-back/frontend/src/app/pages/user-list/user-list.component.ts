import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {User} from "../../services/user";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

  users: User[] = [];

  constructor(private userService:UserService) { }

  ngOnInit(): void {
    this.retrieveUsers();
  }

  private retrieveUsers() {
    this.userService.findAll().subscribe(users => {
      this.users = users;
    }, error => {
      console.log(error);
    })
  }

  delete(id: number | null) {
    if (id !== null)
      this.userService.deleteById(id).subscribe(() => {
        this.retrieveUsers();
      }, error => {
        console.log(error);
      });
  }

}
