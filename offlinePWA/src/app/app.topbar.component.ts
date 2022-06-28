import {Component, OnDestroy} from '@angular/core';
import { AppComponent } from './app.component';
import { AppMainComponent } from './app.main.component';
import { BreadcrumbService } from './app.breadcrumb.service';
import { Subscription } from 'rxjs';
import { MenuItem } from 'primeng/api';
import { SyncService } from './services/sync.service';
import { UserService } from './services/user.service';
import { UtilService } from './services/util.service';
import { Location } from '@angular/common'


@Component({
  selector: 'app-topbar',
  templateUrl: './app.topbar.component.html'
})
export class AppTopBarComponent implements OnDestroy{

    subscription: Subscription;
    lastSyncDate: Date;
    items: MenuItem[];
    dirtyDataCount = "0";
    conectionStatus = "NO";

    constructor(public breadcrumbService: BreadcrumbService, public app: AppComponent, public appMain: AppMainComponent, private syncService: SyncService, private utilService: UtilService, private location: Location,
        public userService: UserService) {
        this.subscription = breadcrumbService.itemsHandler.subscribe(response => {
            this.items = response;
        });

        this.loadData();

    }

    ngOnDestroy() {
        if (this.subscription) {
            this.subscription.unsubscribe();
        }
    }

    async sync(){
        if(!this.syncService.connectionService()) 
            this.utilService.warnMessage("Error de conexión", "Asegúrece que esté conectado al internet");
        await this.syncService.sync();
        this.loadData();
    }

    loadData(){
        this.lastSyncDate = new Date(localStorage.getItem("lastSyncDate"));
        let tempDirtyCount = localStorage.getItem("dirtyCountData");
        if(tempDirtyCount!=null) this.dirtyDataCount = tempDirtyCount;
        if(this.syncService.connectionService()) this.conectionStatus = "";
        else this.conectionStatus = "NO";
    }

    opentTopBar(event){
        this.loadData();
        this.appMain.onTopbarNotificationMenuButtonClick(event);
    }
    
    back(){
        this.location.back();
    }

    logout(){
        this.userService.logout();
    }

    
}
