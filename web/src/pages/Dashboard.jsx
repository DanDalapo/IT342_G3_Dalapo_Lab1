import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './css/Dashboard.css';
import { FiHome, FiUser, FiSettings, FiLogOut } from 'react-icons/fi';

const Dashboard = () => {
    const navigate = useNavigate();
    const [userData, setUserData] = useState(null);

    useEffect(() => {
        // 1. Check local storage for the logged-in user
        const storedUser = localStorage.getItem('user');
        
        if (storedUser) {
            // 2. If they exist, parse the JSON and set the state
            setUserData(JSON.parse(storedUser));
        } else {
            // 3. SECURITY: If no user is found, kick them back to login!
            navigate('/');
        }
    }, [navigate]);

    const handleLogout = () => {
        // Clear the storage and send them back to the login page
        localStorage.removeItem('user');
        navigate('/');
    };

    // Show a blank screen for a split second while checking local storage
    if (!userData) return null; 

    return (
        <div className="dashboard-container">
            <nav className="sidebar">
                <div className="sidebar-header">
                    Mini App
                </div>
                
                <div className="nav-menu">
                    <div className="nav-item active">
                        <FiHome /> <span>Dashboard</span>
                    </div>
                    <div className="nav-item">
                        <FiUser /> <span>Profile</span>
                    </div>
                    <div className="nav-item">
                        <FiSettings /> <span>Settings</span>
                    </div>
                </div>

                <button className="logout-btn" onClick={handleLogout}>
                    <FiLogOut /> <span>Log out</span>
                </button>
            </nav>

            <main className="main-content">
                <div className="welcome-header">
                    <h1>Welcome back, {userData.firstName}!</h1>
                    <p>Here is an overview of your account.</p>
                </div>

                <div className="info-card">
                    <div className="info-row">
                        <div className="info-label">Full Name</div>
                        <div className="info-value">{userData.firstName} {userData.lastName}</div>
                    </div>
                    <div className="info-row">
                        <div className="info-label">Email Address</div>
                        <div className="info-value">{userData.email}</div>
                    </div>
                    <div className="info-row">
                        <div className="info-label">Date of Birth</div>
                        <div className="info-value">{userData.dateOfBirth}</div>
                    </div>
                </div>
            </main>

        </div>
    );
};

export default Dashboard;