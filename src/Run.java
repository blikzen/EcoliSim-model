public class Run {	
	
	public static void main(String[] args) {
		
			int find_loc;
			String holder;
			
			String[] options = {"-n=","-t=","-s=","-oavg=","-ofull=","-mx=","-my=","-ipos=","-iposx=","-iposy=","-orient=","-grad=","-sourcex=","-sourcey=","-max=","-min=","-rate=","-sigma=","-step=","-add=","-rem=","-med=","-bound=","-nmotor=","-diff=","-speed=","-rest=","-chez=","-zphos=","-chey=","-yphos=","-cheb=","-bcat=","-cher=","-rcat=","-precision=","-adaptrate=","-chea=","-autophos=","-taroff=","-taron=","-tsroff=","-tsron=","-tar=","-tsr=","-inimeth=","-int=","-tumble=","-run=","-motor=","--help"};

			double time_sim = 500, matrix_x = 20, matrix_y = 20, ini_pos_x = 10, ini_pos_y = 10, source_x = 10, source_y = 10, att_max = 0.1, att_min = 0, att_rate = 0.001, g_sigma = 5, step_val = 0.03, add_step = 5, rem_step = 350, diff_const = 0.062, cell_velocity = 20, rest_rate = 0.65, chez = 100, zphos = 30, chey = 100, yphos = 100, cheb = 100, bcat = 0.0364, cher = 100, rcat = 0.0182, precision = 100, adapt_rate = 1, chea = 100, autophos = 5, tar_off = 0.02, tar_on = 0.5, tsr_off = 100, tsr_on = 1000000, ini_methyl =0.924, integration_rate = 0.01;
		
			int n_cells = 1, position_cells = 0, orient_cells = 4, grad_select = 0, medium_num = 0, bound_select = 0, n_motors = 5, tar_num = 6, tsr_num = 12, tumble_select = 0, run_select = 0, motor_select = 0;
		
			long rand_seed = 3;
	
			String avgname = "averages.out", fullname = "individuals.out";
			
				for(int i = 0; i < args.length; i = i+1){
					for(int y = 0; y < options.length;y = y+1){
						Boolean found = false;
						found = args[i].contains(options[y]);
						if (found == true){
							found = false;
							switch (y){
								case 50: System.out.println("\nRAPIDCELL originally written by Nikita Vladimirov, PhD, and bareboned / converted to commandline utility by Martin Forde \n\n"+
															"******************************************************************************************************\n"+
															"Default values are denoted by (*), optional variables are indented under their corresponding functions\n"+
															"******************************************************************************************************\n\n"+
															"BASICS\n"+
															"	-n=(1)				-- number of cells\n"+
															"	-t=(500)			-- time of simulation (s)\n"+
															"	-s=(3)				-- random number seed\n"+
															"	-oavg=(averages.out)		-- output averages\n"+
															"	-ofull=(individuals.out)	-- full output\n"+
															"\n"+
															"STARTING MATRIX\n"+
															"	-mx=(20)			-- x range (mm)\n"+
															"	-my=(20)			-- y range (mm)\n"+
															"\n"+
															"STARTING POSITIONS\n"+
															"	-ipos=(0)			-- center\n"+
															"	-ipos=1				-- left center\n"+
															"	-ipos=2				-- custom\n"+
															"		-iposx=(10)		-- custom x (mm)\n"+
															"		-iposy=(10)		-- custom y (mm)\n"+
															"\n"+
															"STARTING ORIENTATION\n"+
															"	-orient=0			-- right\n"+
															"	-orient=1			-- up\n"+
															"	-orient=2			-- left\n"+
															"	-orient=3			-- down\n"+
															"	-orient=(4)			-- random\n"+
															"\n"+
															"GRADIENT\n"+
															"	-grad=(0)			-- no gradient\n"+
															"	-grad=1				-- constant activity C(x,y) = K*C*r/\n"+
															"						((K_on-Koff)/K-C*r), \n"+
															"						r^2=(x-Lx/2)^2+(y-Ly/2)^2,\n"+
															"						K and C dep. on Lx and K_on{off}\n"+
															"	-grad=2				-- constant activity C(x) = K*C*x/\n"+
															"						((K_on-Koff)/K-C*x), \n"+
															"						where K and C constant,\n "+
															"						dep. on Lx and K_on{off}\n"+
															"	-grad=3				-- constant activity C(t) = K*C*t/\n"+
															"						((K_on-Koff)/K-C*t), \n"+
															"						where K and C constant,\n "+
															"						dep. on Lx and K_on{off}\n"+
															"	-grad=4				-- linear F(x)=(Max-Min)*x/Lx\n"+
															"		-sourcex=(10)		-- x location of source\n"+
															"		-sourcey=(10)		-- y location of source\n"+
															"		-max=(0.1)		-- max attractant mM\n"+
															"		-min=(0)		-- min attractant mM\n"+
															"		-rate=(0.001)		-- rate of attr. adding, mM/s\n"+
															"	-grad=5				-- Gaussian G(x,y) = Max*\n"+
															"						exp(-r^2/(2*sigma^2), \n"+
															"						r^2=(x-Xs)^2+(y-Ys)^2\n"+
															"		-sourcex=(10)		-- x location of source\n"+
															"		-sourcey=(10)		-- y location of source\n"+
															"		-sigma=(5)		-- Gaussian standard deviation\n"+
															"		-max=(0.1)		-- max attractant mM\n"+
															"		-rate=(0.001)		-- rate of attr. adding, mM/s\n"+
															"	-grad=6				-- Gaussian G(x) = Max*\n"+
															"						exp(-(x-Xs)^2/(2*sigma^2)\n"+
															"		-sourcex=(10)		-- x location of source\n"+
															"		-sigma=(5)		-- Gaussian standard deviation\n"+
															"		-max=(0.1)		-- max attractant mM\n"+
															"		-rate=(0.001)		-- rate of attr. adding, mM/s\n"+
															"	-grad=7				-- exponential E(t) = Rate*exp(t)\n"+
															"		-rate=(0.001)		-- rate of attr. adding, mM/s\n"+
															"	-grad=8				-- exponential E(x) = Rate*exp(x)\n"+
															"		-rate=(0.001)		-- rate of attr. adding, mM/s\n"+
															"	-grad=9				-- stepwise add/removal\n"+
															"		-step=(0.03)		-- attractant step, mM\n"+
															"		-add=(50)		-- time for addition, s\n"+
															"		-rem=(350)		-- time for removal, s\n"+
															"\n"+
															"MOTILITY\n"+
															"	-nmotor=(5)			-- number of flagellar motors\n"+
															"	-diff=(0.062)			-- rot. diffusion (rad2/s)\n"+
															"	-speed=(20)			-- cell speed (mcm/s)\n"+
															"	-rest=(0.65)			-- resting mb (ccw)\n"+
															"\n"+
															"	MEDIUM\n"+
															"		-med=(0)		-- liquid\n"+
															"		-med=1			-- agar\n"+
															"\n"+
															"	BOUNDARY\n"+
															"		-bound=(0)		-- reflective\n"+
															"		-bound=1		-- periodic\n"+
															"\n"+
															"NETWORK\n"+
															"	-chea=(100)			-- relative expression of CheA\n"+
															"		-autophos=(5)		-- autophosph. rate\n"+
															"	-cheb=(100)			-- relative expression of CheB\n"+
															"		-bcat=(0.0364)		-- CheB catalytic rate\n"+
															"	-cher=(100)			-- relative expression of CheR\n"+
															"		-rcat=(0.0182)		-- CheR catalytic rate\n"+
															"	-chey=(100)			-- relative expression of CheY\n"+
															"		-yphos=(100)		-- CheY phosph. rate\n"+
															"	-chez=(100)			-- relative expression of CheZ\n"+
															"		-zphos=(30)		-- CheZ phosph. rate\n"+
															"	-precision=(100)		-- adaptation precision, %\n"+
															"		-adaptrate=(1)		-- adapt. rate, relative\n"+
															"\n"+
															"	RECEPTOR LIGAND KD-S (mM)\n"+
															"		TAR	\n"+
															"			-taroff(0.02)	--tar koff\n"+
															"			-taron(0.5)	--tar kon\n"+
															"		TSR\n"+
															"			-tsroff(100)	--tsr koff\n"+
															"			-tsron(1000000)	--tsr kon\n"+
															"\n"+
															"CLUSTER COMPOSITION\n"+
															"	-tar=(6)\n"+
															"	-tsr=(12)\n"+
															"	-inimeth=(0.924)		--initial methylation\n"+
															"\n"+
															"ALGORITHM\n"+
															"	-int=(0.01)			-- integration dt, s\n\n"+
															"	MODEL OF TUMBLE\n"+
															"		-tumble=(0)		-- isotropic\n"+
															"		-tumble=1		-- anisotropic\n"+
															"	MODEL OF RUN\n"+
															"		-run=(0)		-- voting motors\n"+
															"		-run=1			-- distortion factor\n"+
															"	MODEL OF MOTOR\n"+
															"		-motor=(0)		-- T_ccw(CheYp), T_cw is constant\n"+
															"		-motor=1		-- T_ccw(CheYp) and T_cw(CheYp)\n\n");System.exit(0);
								case 0: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); n_cells = Integer.parseInt(holder);
								case 1: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); time_sim = Double.parseDouble(holder);
								case 2: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); rand_seed = Long.parseLong(holder);
								case 3: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); avgname = holder;
								case 4: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); fullname = holder;
								case 5: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); matrix_x = Double.parseDouble(holder);
								case 6: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); matrix_y = Double.parseDouble(holder);
								case 7: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); position_cells = Integer.parseInt(holder);
								case 8: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); ini_pos_x = Double.parseDouble(holder);
								case 9: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); ini_pos_y = Double.parseDouble(holder);
								case 10: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); orient_cells = Integer.parseInt(holder);
								case 11: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); grad_select = Integer.parseInt(holder);
								case 12: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); source_x = Double.parseDouble(holder);
								case 13: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); source_y = Double.parseDouble(holder);
								case 14: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); att_max = Double.parseDouble(holder);
								case 15: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); att_min = Double.parseDouble(holder);
								case 16: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); att_rate = Double.parseDouble(holder);
								case 17: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); g_sigma = Double.parseDouble(holder);
								case 18: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); step_val = Double.parseDouble(holder);
								case 19: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); add_step = Double.parseDouble(holder);
								case 20: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); rem_step = Double.parseDouble(holder);
								case 21: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); medium_num = Integer.parseInt(holder);
								case 22: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); bound_select = Integer.parseInt(holder);
								case 23: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); n_motors = Integer.parseInt(holder);
								case 24: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); diff_const = Double.parseDouble(holder);
								case 25: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); cell_velocity = Double.parseDouble(holder);
								case 26: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); rest_rate = Double.parseDouble(holder);
								case 27: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); chez = Double.parseDouble(holder);
								case 28: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); zphos = Double.parseDouble(holder);
								case 29: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); chey = Double.parseDouble(holder);
								case 30: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); yphos = Double.parseDouble(holder);
								case 31: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); cheb = Double.parseDouble(holder);
								case 32: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); bcat = Double.parseDouble(holder);
								case 33: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); cher = Double.parseDouble(holder);
								case 34: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); rcat = Double.parseDouble(holder);
								case 35: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); precision = Double.parseDouble(holder);
								case 36: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); adapt_rate = Double.parseDouble(holder);
								case 37: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); chea = Double.parseDouble(holder);
								case 38: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); autophos = Double.parseDouble(holder);
								case 39: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); tar_off = Double.parseDouble(holder);
								case 40: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); tar_on = Double.parseDouble(holder);
								case 41: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); tsr_off = Double.parseDouble(holder);
								case 42: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); tsr_on = Double.parseDouble(holder);
								case 43: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); tar_num = Integer.parseInt(holder);
								case 44: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); tsr_num = Integer.parseInt(holder);
								case 45: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); ini_methyl = Double.parseDouble(holder);
								case 46: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); integration_rate = Double.parseDouble(holder);
								case 47: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); tumble_select = Integer.parseInt(holder);
								case 48: find_loc = args[i].indexOf("=")+1; holder = args[i].substring(find_loc); run_select = Integer.parseInt(holder);
							}
						}
					}
				}
				
				Thread th1;
				th1 = new Thread();
				Model Model1 = new Model(th1);
				Model1.setNcells(n_cells);
				Model1.setTmax(time_sim);
				Model1.setLxLy(time_sim,matrix_y);
				Model1.setInitialCellDistribution(ini_pos_x, ini_pos_y,orient_cells);
				Model1.setGradient(grad_select);
				Model1.setStepGradientParams(step_val, add_step, rem_step);
				Model1.setGradMinMaxVal(att_min, att_max);
				Model1.setGradSourceXY(source_x, source_y);
				Model1.setExpGradRate(att_rate);
				Model1.setGradSigma(g_sigma);
				Model1.setMedium(medium_num);
				Model1.setOutputDt(att_max);
				Model1.setOutFileNames(avgname, fullname);
				Model1.setOutFlags(true, true, true, true, true, true, true, true, true, true, true, true);
				Model1.setDt(att_max);
				Model1.setRandSeed(rand_seed);
				Cell2D.setRandSeed(rand_seed);
				Network.setDissConstants(tar_off, tar_on, tsr_off, tsr_on);
				Model1.setRunModel(run_select);
				Model1.setTumbleModel(tumble_select);
				Model1.setBoundaryCondition(bound_select);
				Cell.setNmotors(n_motors);
				Cell.setRotDiffusionCoeff(diff_const);
				Cell.setMaxCellVelocity(1e-3*cell_velocity);
				Motor.setMotorBias0(rest_rate,motor_select);
				Model1.setAdaptRate(adapt_rate);
				Network.setAdaptPrecision(0.01*precision);
				Network.setRelativeCheRCheB(0.01*cher, 0.01*cheb);
				Network.setNreceptors(tar_num, tsr_num);
				Network.setRelativeCheYCheZ(0.01*chey, 0.01*chez);
				Network.setIniMethState(ini_methyl);
				Network.setRelativeCheA(0.01*chea);
				Network.setRates(rcat, bcat, autophos, yphos, zphos);
				try{
					Model1.runModel();
				}
				catch (InterruptedException e) {
					return;
				}
	}
}
		
