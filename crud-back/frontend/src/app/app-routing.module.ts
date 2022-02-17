import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UserListComponent} from "./pages/user-list/user-list.component";
import {UserFormComponent} from "./pages/user-form/user-form.component";

const routes: Routes = [
  {path: "", pathMatch: "full", redirectTo: "user"},
  {path: "user", component: UserListComponent},
  {path: "user/:id", component: UserFormComponent},
  {path: "user/new", component: UserFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
