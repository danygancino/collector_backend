import {Component} from '@angular/core';
import {ConfirmationService, SelectItem} from 'primeng/api';
import {AppMainComponent} from './app.main.component';
import { User, Role } from './models/userModel';
import { UserService } from './services/user.service';

@Component({
    selector: 'app-rightmenu',
    templateUrl: './app.rightmenu.component.html'
})
export class AppRightmenuComponent{
    user: User;
    roles: Role[];

    constructor(public appMain: AppMainComponent, private userService: UserService, private confirmationService: ConfirmationService) {
        this.user = userService.getLogedUser().getValue();
        console.log(this.user);
        if(this.user != null){
            this.roles = this.user.roles;
        }
    }

    confirm() {
        this.confirmationService.confirm({
            message: 'Al salir, se borrarán todos los datos que no se han sincronizado. \nEstá seguro que desea salir? ',
            accept: () => {
                this.userService.logout();
                this.userService.deleteAllData();
            },
            acceptLabel: "Si",
            rejectLabel: "No",
            acceptButtonStyleClass: "p-button-danger"
        });
    }

    deleteAllData() {
        this.confirmationService.confirm({
            header: "Borrar todos los datos",
            message: '¿Está seguro que desea borrar todos los datos? ',
            accept: () => {
                this.userService.deleteAllData();
            },
            acceptLabel: "Si",
            rejectLabel: "No",
            acceptButtonStyleClass: "p-button-danger"
        });
    }
}
