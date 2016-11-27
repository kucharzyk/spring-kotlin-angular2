import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BaseRequestOptions, Http} from '@angular/http';
import {BrowserModule} from '@angular/platform-browser';
import {MockBackend} from '@angular/http/testing';
import {ClarityModule} from 'clarity-angular';

export const COMMON_TESTING_MODULES = [
  BrowserModule,
  FormsModule,
  ReactiveFormsModule,
  ClarityModule
];

export const COMMON_TESING_PROVIDERS = [
  BaseRequestOptions,
  MockBackend,
  {
    provide: Http,
    useFactory: function (backend, defaultOptions) {
      return new Http(backend, defaultOptions);
    },
    deps: [MockBackend, BaseRequestOptions]
  },
];
