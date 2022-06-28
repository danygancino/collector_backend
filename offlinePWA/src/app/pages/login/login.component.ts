import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { rolesEnum } from 'src/app/models/role.enum';
import { CatalogoService } from 'src/app/services/catalogo.service';
import { UserService } from 'src/app/services/user.service';
import { UtilService } from 'src/app/services/util.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class AppLoginComponent implements OnInit {

  public loginForm = this.fb.group({
    username: ['', [Validators.required]],
    password: ['', [Validators.required]]
  });

  submitted: boolean = false;


  constructor(public app: AppComponent,
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router,
    private utilService: UtilService,
    private catalogoService: CatalogoService

  ) {
  }

  ngOnInit(): void {
    try {
      if(this.userService.getLogedUsername() != null){
        this.redirectByRole();
      }
    } catch (error) {
      this.userService.logout();
    }
   
  }
  redirectByRole() {
    if(this.userService.hasRole(rolesEnum.administrador))
        this.router.navigateByUrl('/pages/administration/reporte');
    else if(this.userService.hasRole(rolesEnum.operador))
        this.router.navigateByUrl('/pages/listaBeneficiarios');
    else {
      this.utilService.warnMessage('Error de credenciales', "El usuario no tiene los permisos de acceso");
      this.userService.logout();
    };
  }

  login(): void {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    } else {
      this.userService.login(this.loginForm.value).subscribe(value => {
        //this.catalogoService.fillCatalogo();
        //this.catalogoService.fillFakeCatalogo();
        this.redirectByRole();
      }, error => {
        if (error.status == 0) {
          this.utilService.warnMessage('Error de conexión', "Espere unos minutos y vuelva a intentar");
        }
        if (error.status == 400) {
          this.utilService.warnMessage('Error', "Contáctese con el administrador");
        }
        if (error.status == 401) {
          this.utilService.warnMessage('Error de credenciales', "usuario o contraseña incorrecta");
        }

      });
    }

  }

}


