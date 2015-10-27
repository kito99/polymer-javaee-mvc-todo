module.exports = function(grunt) {

    grunt.initConfig({
		exec: {
		  readme: 'asciidoctor "README.asc"',
		  labSetup: 'asciidoctor "lab-setup.asc"',	
            
		  session: 'asciidoctor -T asciidoctor-reveal.js/templates/slim "hacking-web-components-session.asc"',
                        
		  intro: 'asciidoctor -T asciidoctor-reveal.js/templates/slim "hacking-web-components-intro.asc"',
		  elements: 'asciidoctor -T asciidoctor-reveal.js/templates/slim "hacking-web-components-elements.asc"',	
		  imports: 'asciidoctor -T asciidoctor-reveal.js/templates/slim "hacking-web-components-imports.asc"',				
		  templates: 'asciidoctor -T asciidoctor-reveal.js/templates/slim "hacking-web-components-templates.asc"',										
		  shadowDom: 'asciidoctor -T asciidoctor-reveal.js/templates/slim "hacking-web-components-shadow-dom.asc"',
		  polymerOverview: 'asciidoctor -T asciidoctor-reveal.js/templates/slim "hacking-web-components-polymer-overview.asc"',            
            
          customElementsLab: 'asciidoctor "../labs/custom-elements-starter/README.asc"',
		  puttingItAllTogetherLab: 'asciidoctor "../labs/putting-it-all-together-starter/README.asc"',
          poylmerElementsLab: 'asciidoctor "../labs/polymer-elements-starter/README.asc"',
		},
        'connect': {
            demo: {
                options: {
                    open: true,
                    keepalive: true,
					base: ".."	
                }
            }
        }
    });

	grunt.loadNpmTasks('grunt-exec');
    grunt.loadNpmTasks('grunt-contrib-connect');
    grunt.registerTask('server', ['connect']);
	
	grunt.registerTask('build-readme', ['exec:readme']);	
	grunt.registerTask('build-lab-setup', ['exec:labSetup']);			
	
	grunt.registerTask('build-custom-elements-lab', ['exec:customElementsLab']);	
	grunt.registerTask('build-putting-it-all-together-lab', ['exec:puttingItAllTogetherLab']);	
	grunt.registerTask('build-polymer-elements-lab', ['exec:poylmerElementsLab']);	
    grunt.registerTask('build-labs', ['build-custom-elements-lab', 'build-putting-it-all-together-lab', 'build-polymer-elements-lab']);	

	grunt.registerTask('build-intro', ['exec:intro']);
	grunt.registerTask('build-elements', ['exec:elements']);	
	grunt.registerTask('build-imports', ['exec:imports']);		
	grunt.registerTask('build-templates', ['exec:templates']);	
	grunt.registerTask('build-shadow-dom', ['exec:shadowDom']);		
    grunt.registerTask('build-polymer-overview', ['exec:polymerOverview']);
	grunt.registerTask('build-slides', ['build-readme', 'build-lab-setup', 'build-intro', 'build-elements', 'build-imports', 'build-templates', 'build-shadow-dom', 'build-polymer-overview']);				
    
    grunt.registerTask('build-session', ['exec:session']);
        
	grunt.registerTask('build-all', ['build-slides', 'build-labs', 'build-session']);	
    
};
