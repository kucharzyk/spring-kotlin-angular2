import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {BrowserModule} from '@angular/platform-browser';
import {CommonModule} from '@angular/common';

export const COMMON_ROOT_MODULES = [
  BrowserModule,
  FormsModule,
  HttpModule
];

export const COMMON_CHILD_MODULES = [
  CommonModule,
  FormsModule,
  HttpModule
];
