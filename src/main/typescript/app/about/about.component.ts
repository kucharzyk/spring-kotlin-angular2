import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'shardis-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.scss']
})
export class AboutComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
    console.log('hello `About` component');
  }

}
