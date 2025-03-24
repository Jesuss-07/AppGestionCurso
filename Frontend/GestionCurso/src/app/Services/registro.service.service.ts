import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RegistroServiceService {

  private apiUrl = 'jdbc:mysql://localhost:3306/gestioncurso';

  constructor() { }
}
