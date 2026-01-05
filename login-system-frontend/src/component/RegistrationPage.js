import React, { useState } from 'react';
import axios from 'axios';
import '../App.css';

function RegistrationPage() {
  const [firstname, setFirstName] = useState('');
  const [lastname, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [registrationSuccess, setRegistrationSuccess] = useState(false);

  const handleRegistration = async () => {
    try {
      const userData = {
        firstname,
        lastname,
        email,
        password
      };

      // ✅ ONLY CHANGE: correct port & endpoint
      const response = await axios.post(
        'http://127.0.0.1:8080/api/v1/customer/register',
        userData
      );

      console.log('User registered:', response.data);
      setRegistrationSuccess(true);

    } catch (error) {
      console.error('Registration error:', error);
      alert('Registration failed');
    }
  };

  if (registrationSuccess) {
    return (
      <div className='centered-container'>
        <div className="login-container">
          <h1>Registrazione effettuata con successo!</h1>
          <h2> </h2>
          <div className="registration-link">
            <p>Per accedere al tuo nuovo account <a href="/">Login here</a></p>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="centered-container">
      <div className="login-container">
        <h2>Registration</h2>

        <div className="input-container">
          <input
            type="text"
            placeholder="Firstname"
            value={firstname}
            onChange={(e) => setFirstName(e.target.value)}
          />
        </div>

        <div className="input-container">
          <input
            type="text"
            placeholder="Lastname"
            value={lastname}
            onChange={(e) => setLastName(e.target.value)}
          />
        </div>

        <div className="input-container">
          <input
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>

        <div className="input-container">
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>

        <button className="login-button" onClick={handleRegistration}>
          Register
        </button>

        <div className="login-link">
          <p className="registration-link">
            Already have an account? <a href="/">Login here</a>
          </p>
        </div>
      </div>
    </div>
  );
}

export default RegistrationPage;
