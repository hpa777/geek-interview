import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "./user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(public http: HttpClient) { }

  public findAll() {
    return this.http.get<User[]>("/api/v1/user/all");
  }

  public findById(id:number) {
    return this.http.get<User>(`/api/v1/user/${id}/id`);
  }

  public deleteById(id:number) {
    return this.http.delete(`/api/v1/user/${id}/id`);
  }

  public save(user: User) {
    if (user.id == null) {
      return this.http.post("/api/v1/user", user);
    } else {
      return this.http.put("/api/v1/user", user);
    }
  }

}
