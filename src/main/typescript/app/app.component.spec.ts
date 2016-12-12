/* tslint:disable:no-unused-variable */

import {TestBed, ComponentFixture, async} from '@angular/core/testing';
import {AppComponent} from './app.component';
import {AuthService} from './shared/auth/auth.service';
import {RouterTestingModule} from '@angular/router/testing';
import {COMMON_TESTING_MODULES, COMMON_TESING_PROVIDERS} from './testing/testing.modules';

describe('App: ShardisUi', () => {

  let fixture: ComponentFixture<AppComponent>;
  let element: any;
  let component: AppComponent;

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
        }])
      ],
      declarations: [
        AppComponent
      ]
    });

    fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    element = fixture.debugElement.nativeElement;
    component = fixture.componentInstance;
  });

  afterEach(() => {
    fixture.destroy();
  });

  it('should create the app', () => {
    expect(component).toBeTruthy();
  });


  it(`should have as url ''https://github.com/shardis''`, () => {
    expect(component.url).toEqual('https://github.com/shardis');
  });

  it('should render github link', () => {
    expect(element.querySelector('footer span a').textContent).toContain('by Shardis');
  });

  it('should properly log you out', async(() => {
    expect(component).toBeTruthy();
    expect(component.authService).toBeTruthy();

    spyOn(component.authService, 'logout');
    expect(component.authService.logout).not.toHaveBeenCalled();

    component.logMeOut();

    expect(component.authService.logout).toHaveBeenCalled();

  }));


});
