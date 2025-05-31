import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-register-user',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule,
    PasswordModule,
    InputTextModule,
    ButtonModule
  ],
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css'] // Asegúrate que sea exactamente así
})
export class RegisterUserComponent {
  form: FormGroup;

  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, this.passwordValidator]],
      confirmPassword: ['', Validators.required]
    }, { validator: this.passwordMatchValidator });
  }

  passwordValidator(control: any) {
    const value = control.value;
    const valid = value.length >= 8 && 
                 /[A-Z]/.test(value) && 
                 /[a-z]/.test(value) && 
                 /[0-9]/.test(value) && 
                 /[!@#$%^&*(),.?":{}|<>]/.test(value);
    return valid ? null : { invalidPassword: true };
  }

  passwordMatchValidator(g: FormGroup) {
    return g.get('password')?.value === g.get('confirmPassword')?.value 
      ? null : { mismatch: true };
  }

  onSubmit() {
    if (this.form.valid) {
      console.log('Datos válidos:', this.form.value);
    }
  }

  contieneMayuscula(): boolean {
  return /[A-Z]/.test(this.form.get('password')?.value);
}

contieneMinuscula(): boolean {
  return /[a-z]/.test(this.form.get('password')?.value);
}

contieneNumero(): boolean {
  return /[0-9]/.test(this.form.get('password')?.value);
}

contieneCaracterEspecial(): boolean {
  return /[!@#$%^&*(),.?":{}|<>]/.test(this.form.get('password')?.value);
}

}