/* tslint:disable:no-unused-variable */

import {TestBed} from '@angular/core/testing';
import {AppComponent} from './app.component';
import {AuthService} from './shared/auth/auth.service';
import {RouterTestingModule} from '@angular/router/testing';
import {COMMON_TESTING_MODULES, COMMON_TESING_PROVIDERS} from './testing/testing.modules';

describe('App: ShardisUi', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        ...COMMON_TESING_PROVIDERS,
        AuthService
      ],
      imports: [
        ...COMMON_TESTING_MODULES,
        RouterTestingModule.withRoutes([{
          path: '',
          pathMatch: 'prefix',
          component: AppComponent
        }]),
      ],
      declarations: [
        AppComponent
      ],
    });
  });

  it('should create the app', () => {
    let fixture = TestBed.createComponent(AppComponent);
    let app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });


  it(`should have as url ''https://github.com/shardis''`, () => {
    let fixture = TestBed.createComponent(AppComponent);
    let app = fixture.debugElement.componentInstance;
    expect(app.url).toEqual('https://github.com/shardis');
  });

  it('should render github link', () => {
    let fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    let compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('md-card.footer a').textContent).toContain('by Shardis');
  });

  it('should properly log you out', () => {
    let fixture = TestBed.createComponent(AppComponent);
    let app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
    expect(app.authService).toBeTruthy();
    expect(app.authService).toBeTruthy();

    spyOn(app.authService, 'logout');
    expect(app.authService.logout).not.toHaveBeenCalled();

    app.logMeOut();

    expect(app.authService.logout).toHaveBeenCalled();

  });


});
