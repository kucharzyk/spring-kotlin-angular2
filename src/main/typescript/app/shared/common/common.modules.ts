import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {BrowserModule} from '@angular/platform-browser';
import {CommonModule} from '@angular/common';
import {ClarityModule} from 'clarity-angular';

export const COMMON_ROOT_MODULES = [
  BrowserModule,
  FormsModule,
  ReactiveFormsModule,
  HttpModule,
  ClarityModule
];

export const COMMON_CHILD_MODULES = [
  CommonModule,
  FormsModule,
  ReactiveFormsModule,
  HttpModule,
  ClarityModule
];
