import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { register } from 'module';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})

export class UsuarioService {
    private url = 'jdbc:mysql://localhost:3306/gestioncurso/res/curso'

    constructor(private http: HttpClient) { }

    registerUser(usuario: any): Observable<any> {
        return this.http.post(
            `${this.url}/create`,
            usuario,
            { observe: 'response' }
        );
    }
}

