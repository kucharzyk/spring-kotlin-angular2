import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";
import {AppMenuItem} from "../../app.menu";

@Injectable()
export class AuthService {

  private authenticated: boolean = false;
  private tokenExpirationDate: Date = null;
  private userData: any = null;

  // @LocalStorage()
  private jwtToken: string;

  public static decodeAccessToken(access_token: string) {
    return JSON.parse(window.atob(access_token.split('.')[1]));
  }

  constructor(public http: Http) {
    this.jwtToken = localStorage.getItem('tokenData');
    if (!!this.jwtToken) {
      this.authenticated = true;
      this.userData = AuthService.decodeAccessToken(this.jwtToken);
      this.tokenExpirationDate = new Date(this.userData.exp * 1000);
      if (this.authenticated && this.tokenExpirationDate < new Date()) {
        console.log('Session timeout');
        this.logout();
      }
    }
  }

  public isAuthenticated(): boolean {
    this.checkTokenExpirationDate();
    return this.authenticated;
  }

  public authenticate(username: string, password: string): Promise<string> {

    console.log('Authentication pending...');

    return new Promise<string>((resolve, reject) => {

      if (!username.trim()) {
        reject('Username cannot be blank');
      }
      if (!password.trim()) {
        reject('Password cannot be blank');
      }

      let headers = new Headers();
      headers.append('Content-Type', `application/x-www-form-urlencoded`);

      let payload = 'username=' + encodeURIComponent(username) + '&password=' + encodeURIComponent(password);

      this.http
        .post('/api/authentication', payload, {headers: headers})
        .subscribe(
          data => {
            this.jwtToken = data.text();
            this.authenticated = true;
            this.userData = AuthService.decodeAccessToken(this.jwtToken);
            this.tokenExpirationDate = new Date(this.userData.exp * 1000);
            resolve('OK');
            localStorage.setItem('tokenData', this.jwtToken);
          },
          err => {
            console.log(err);
            reject('Username and password doesn\'t match');
          }
        );

    });
  }

  public refreshToken() {
    if (this.isAuthenticated()) {
      // refresh token here
    }
  }

  public logout(): any {
    this.jwtToken = null;
    localStorage.removeItem('tokenData');
    this.userData = null;
    this.authenticated = false;
    this.tokenExpirationDate = null;
  }

  public getUserData(): any {
    return this.userData;
  }

  public getTokenExpirationDate(): Date {
    return this.tokenExpirationDate;
  }

  public hasRole(role: string): boolean {
    if (this.isAuthenticated()) {
      return this.getUserData()['authorities'].indexOf(role) >= 0;
    }
    return false;
  }

  public hasAnyRole(roles: string[]): boolean {
    let ok = false;
    roles.forEach(role => {
      if (this.hasRole(role)) {
        ok = true;
      }
    });
    return ok;
  }

  public canView(view: AppMenuItem): boolean {
    let ok = false;
    if (!view.roles) {
      ok = true;
    } else {
      ok = this.hasAnyRole(view.roles);
    }
    return ok;
  }

  public getAuthorizationHeaders(): Headers {
    let authorizationHeaders = new Headers();
    if (this.authenticated) {
      authorizationHeaders.append('Authorization', `Bearer ${this.jwtToken}`);
    }
    return authorizationHeaders;
  }

  private checkTokenExpirationDate() {
    if (this.authenticated && this.tokenExpirationDate < new Date()) {
      console.log('Session timeout');
      this.logout();
    }
  }

}
