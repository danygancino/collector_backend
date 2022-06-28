import {AfterViewChecked, ChangeDetectionStrategy, ChangeDetectorRef, Component, Input, OnInit, ViewChild} from '@angular/core';
import { Loader } from 'src/app/models/loader';
import {LoaderService} from '../../services/loader.service';



@Component({
  selector: 'app-loader',
  templateUrl: './loader.component.html',
  styleUrls: ['./loader.component.scss'],
  // changeDetection: ChangeDetectionStrategy.OnPush
})
export class LoaderComponent implements OnInit {
  @ViewChild('loader') boxElement;

  @Input() public id = 'global';
  public show: boolean;

  constructor(private loaderService: LoaderService, private cd: ChangeDetectorRef) {
  }

  public ngOnInit(): void {
    this.loaderService.loaderStatus$.subscribe((response: Loader) => {
      this.show = this.id === response.id && response.status;
    });
  }

  ngAfterViewCheckedx(): void {
    const show = this.isShowExpand();
    if (show !== this.show) {
      this.show = show;
      this.cd.detectChanges();
    }
  }

  isShowExpand() {
    const show = this.boxElement && this.boxElement.nativeElement.scrollHeight > this.boxElement.nativeElement.clientHeight;
    // console.log('show: ' + show);
    return show;
  }

}