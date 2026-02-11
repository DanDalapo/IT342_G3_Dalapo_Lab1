import React, { useState } from 'react';
import axios from 'axios';
import './Login.css';

import { FiMail, FiLock, FiGithub } from 'react-icons/fi';
import { RiinfinityLine } from 'react-icons/ri';

const Login = () => {
    
    const [formData, setFormData] = useState({
        email: '',
        password: ''
    });
    
    const [message, setMessage] = useState('');

    // Handle input changes
    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    // Handle Form Submission
    const handleLogin = async (e) => {
        e.preventDefault();
        setMessage(''); // Clear previous messages

        try {
            // This URL must match your Spring Boot Controller
            const response = await axios.post('http://localhost:3000/users/login', {
                email: formData.email,
                password: formData.password
            });

            // If successful
            console.log("Login Success:", response.data);
            alert(`Welcome back, ${response.data.firstName}!`);
            // Here you would normally redirect using: navigate('/dashboard');

        } catch (error) {
            // If error (401 or 404)
            if (error.response) {
                setMessage(error.response.data); // "Invalid Email or Password"
            } else {
                setMessage("Server error. Is the backend running?");
            }
        }
    };

    return (
        <div className="login-container">
            {/* LEFT SIDE - FORM */}
            <div className="login-left">
                <div className="logo-container">
                    <RiinfinityLine className="logo-icon" />
                </div>
                
                <form onSubmit={handleLogin}>
                    
                    {/* Email Field */}
                    <div className="input-group">
                        <label className="input-label">Email</label>
                        <div className="input-wrapper">
                            <FiMail className="input-icon" />
                            <input 
                                type="email" 
                                name="email"
                                placeholder="hello@email.com" 
                                className="styled-input"
                                value={formData.email}
                                onChange={handleChange}
                                required
                            />
                        </div>
                    </div>

                    {/* Password Field */}
                    <div className="input-group">
                        <label className="input-label">Password</label>
                        <div className="input-wrapper">
                            <FiLock className="input-icon" />
                            <input 
                                type="password" 
                                name="password"
                                placeholder="Your password" 
                                className="styled-input"
                                value={formData.password}
                                onChange={handleChange}
                                required
                            />
                        </div>
                    </div>

                    {/* Error Message Display */}
                    {message && <p style={{color: 'red', fontSize: '0.8rem', marginTop: '5px'}}>{message}</p>}

                    <button type="submit" className="login-btn">Login</button>
                    
                    <div className="signup-link">
                        Don't have an account? <a href="/signup">Sign up</a>
                    </div>

                    <div className="divider">
                        <span>or</span>
                    </div>

                    <button type="button" className="github-btn">
                        <FiGithub /> Login with Github
                    </button>
                </form>
            </div>

            {/* RIGHT SIDE - IMAGE */}
            <div className="login-right">
                {/* If you have the image file, import it at the top: import bgImg from '../assets/img.png'
                   and use: <img src={bgImg} alt="Art" className="art-image" />
                */}
                <div className="art-image">
                   {/* This div uses the CSS gradient placeholder currently */}
                </div>
            </div>
        </div>
    );
};

export default Login;