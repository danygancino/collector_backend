import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {MessageService} from 'primeng/api';
import {tap} from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Role, User } from '../models/userModel';
import jwtDecode from 'jwt-decode';
import { BeneficiarioService } from './beneficiario.service';
import { rolesEnum } from '../models/role.enum';
import { EntidadService } from './entity.service';

const base_url = environment.base_url;
const app_code = environment.app_code;


@Injectable({
  providedIn: 'root'
})
export class UserService {


  private currentUserSubject: BehaviorSubject<User>;

  constructor(private http: HttpClient, private router: Router, private messageService: MessageService, private beneficiarioService: BeneficiarioService, private entidadService: EntidadService) {
    this.setUser();
  }

  public login(user: User, remember: boolean = false) {
    return this.http.post(`${base_url}authentication/login`, user)
      .pipe(
        tap(resp => {
          const userF = this.setUser();
          this.setRemember(remember, userF);
        })
      );
  }

  public setUser() {
    const token = this.getToken();
    let decToken: any;
    let user = null;
    if (token) {

      decToken = jwtDecode(token);
      const exp = decToken.exp;
      // verifico expiración token
      if (Date.now() >= exp * 1000000) {
        this.currentUserSubject = new BehaviorSubject<User>(null);
        this.logout();
        this.messageService.add({severity: 'error', summary: 'Su sesión a caducado', detail: 'Por favor vuelva a ingresar al sistema'});
        return;
      }
      user = new User();
      user.id = decToken.id;
      user.username = decToken.sub;
      user.name = decToken.name;
      user.email = decToken.email;
      user.roles = decToken.roles;
      user.projectImplementer = decToken.projectImplementer;
    } else {
      this.logout();
      return;
    }
    if (this.currentUserSubject) {
      this.currentUserSubject.next(user);
    } else {
      this.currentUserSubject = new BehaviorSubject<User>(user);
    }
    return user;
  }

  public setToken(token: string) {
    localStorage.setItem(`${app_code}_token`, token);
  }



  public removeToken() {
    localStorage.removeItem(`${app_code}_token`);
  }

  public getToken() {
    return localStorage.getItem(`${app_code}_token`);
  }

  public setRemember(remember: boolean, user: User) {
    if (remember && user) {
      localStorage.setItem(`${app_code}_username`, user.username);
    }

  }

  public getLogedUsername(): User {
    return this.currentUserSubject.value;
  }

  public getLogedUser(): BehaviorSubject<User> {
    return this.currentUserSubject;
  }

  logout() {
    if (this.currentUserSubject) {
      this.currentUserSubject.next(null);
    }
    this.removeToken();
    this.beneficiarioService.getAllBeneficiarios
    this.router.navigateByUrl('/login');
  }

  hasRole(role: string): boolean {
    const user = this.currentUserSubject.value;
    return user.roles.map(r => r.name.toUpperCase()).indexOf(role.toUpperCase())>=0
  }

  hasAnyRole(roles: string[]): boolean {
    let result = false;
    const user = this.currentUserSubject.value;
    if (user && user.roles && user.roles.length > 0) {
      user.roles.forEach(roleU => {
        if (roles && roles.length > 0) {
          roles.forEach(roleAsked => {
            if (roleU.name.toUpperCase() === roleAsked.toUpperCase()) {
              result = true;
            }
          });
        }
      });
    }
    return result;

  }

  changePassword(oldPassword: string, newPassword: string) {
    const changeRequest = {
      oldPassword,
      newPassword
    };
    return this.http.post(`${base_url}authentication/changepasswordsimple`, changeRequest);


  }

  recoverPassword(email: string) {
    const changeRequest = {
      oldPassword: null,
      newPassword: email
    };
    return this.http.post(`${base_url}authentication/recoverpasswordsimple`, changeRequest);


  }

  public getAllUsers() {
    return this.http.get<User[]>(`${base_url}authentication/users`);
  }

  public getAllRoles() {
    return this.http.get<Role[]>(`${base_url}authentication/roles`);
  }

  public createUser(user: User) {
    return this.http.post(`${base_url}authentication/users`, user);
  }

  public updateUser(user: User) {
    return this.http.put(`${base_url}authentication/users`, user);
  }
  public getActiveUNHCRUsers() {
    return this.http.get<User[]>(`${base_url}authentication/users/active/UNHCR`);
  }
  deleteAllData() {
    this.entidadService.deleteAll();
    localStorage.setItem("dirtyCountData", "0");
    localStorage.removeItem("lastSyncDate");
    location.replace(location.origin);
  }
}
